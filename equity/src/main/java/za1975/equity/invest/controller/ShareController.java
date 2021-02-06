package za1975.equity.invest.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import za1975.equity.invest.assembler.ShareAssembler;
import za1975.equity.invest.entity.Share;
import za1975.equity.invest.model.ShareModel;
import za1975.equity.invest.service.ShareService;

@RestController
@RequestMapping(path = "/{userId}/shares",
consumes = {MediaType.APPLICATION_JSON_VALUE,MediaTypes.HAL_JSON_VALUE},
produces = {MediaType.APPLICATION_JSON_VALUE,MediaTypes.HAL_JSON_VALUE})
@OpenAPIDefinition(info = @Info(title = "Share Api"),tags = @Tag(name = "Share Api"))
public class ShareController {
	
	@Autowired
	private PagedResourcesAssembler<Share> pagedAssembler;
	
	@Autowired
	private ShareAssembler shareAssembler;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@Operation(description = "Get All Available Share for an user",parameters = 
	@Parameter(name = "userId",in = ParameterIn.PATH))
	public ResponseEntity<PagedModel<ShareModel>> getAllShares(@PathVariable(name = "userId")String userId,@PageableDefault(
			direction = Direction.DESC,	
			page = 0,
			size = 10					
			)Pageable defaultPage,	@SortDefault(
			direction = Direction.DESC,
			sort = {"createdOn"}
			) Sort sort){
		return ResponseEntity.ok(pagedAssembler.toModel(shareService.getAllSharesByUserId(userId, defaultPage, sort), shareAssembler));
	}
	
	@GetMapping(path="/{id}")
	@Operation(description = "Get Share Details",parameters = {@Parameter(name = "userId",in = ParameterIn.PATH),@Parameter(name = "id",in = ParameterIn.PATH,description = "share id")})
	public ResponseEntity<?> getShareDetails(@PathVariable(name = "userId")String userId,@PathVariable(name="id")Long id,@RequestHeader(name = "Accept-Language",required = false) Locale locale)
	{
		Optional<Share> entityO=shareService.getShareDetailsById(id);
		if(entityO.isEmpty())
		{
			Problem problem=Problem.create();
			problem.withProperties(p->{
				p.put("user", userId);
				p.put("share_id", id);
				p.put("message", messageSource.getMessage("SH000", new Object[] {id}, LocaleContextHolder.getLocale()));
				
				});
			problem.withStatus(HttpStatus.EXPECTATION_FAILED);
			return ResponseEntity.unprocessableEntity().body(problem);
		}
		Share entity=entityO.get();
		ShareModel model = shareAssembler.toModel(entity);
		if(!entity.getStocks().isEmpty());
			//model.add(null);
		return ResponseEntity.ok(model);
	}

}
