package com.manage.service.mangement.controller;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.service.mangement.equity.model.ShareModel;
import com.manage.service.mangement.equity.service.ShareDataService;

@RestController
@RequestMapping(path = "/{userId}/shares", consumes = { MediaType.APPLICATION_JSON_VALUE,
		MediaTypes.HAL_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
public class ShareController {

	@Autowired
	private ShareDataService shareDataService;

	@GetMapping
	public ResponseEntity<PagedModel<ShareModel>> getShares(@PathVariable(name = "userId") String userId,
			@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable defaultPage,
			@SortDefault(direction = Direction.DESC, sort = { "createdOn" }) Sort sort,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {

		PagedModel<ShareModel> modelPage = shareDataService.getAllShares(userId, defaultPage, sort, locale);
		PageMetadata metadata = modelPage.getMetadata();

		Collection<ShareModel> items = modelPage.getContent();

		items.stream().forEach(i -> {
			// i.removeLinks();
			i.add(WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class, userId).getShares(userId, null, null,
							LocaleContextHolder.getLocale()))
					.withRel(IanaLinkRelations.COLLECTION))
					.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class, userId, i.getId())
							.getShareDetails(userId, i.getId(), null)).withRel(IanaLinkRelations.ITEM));
		});

		return ResponseEntity
				.ok(PagedModel.of(items, metadata,
						WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ShareController.class, userId)
								.getShares(userId, null, null, LocaleContextHolder.getLocale()))
								.withRel(IanaLinkRelations.SELF)));

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getShareDetails(@PathVariable(name = "userId") String userId, @PathVariable("id") Long id,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale) {
		return null;

	}

}
