package za1975.equity.invest.controller;

import java.util.Optional;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import za1975.equity.invest.assembler.InvestmentAssembler;
import za1975.equity.invest.entity.Investment;
import za1975.equity.invest.model.InvestmentModel;
import za1975.equity.invest.service.InvestService;

@RestController
@RequestMapping(
		path = "/invests",
		produces = { MediaTypes.HAL_JSON_VALUE,MediaTypes.ALPS_JSON_VALUE},
		consumes = {MediaTypes.HAL_JSON_VALUE,MediaTypes.COLLECTION_JSON_VALUE}
)
@OpenAPIDefinition(info = @Info(title = "Investment Api"),tags = @Tag(name = "Investment Api"))
public class InvestmentController {

	@Autowired
	private InvestService investmentService;
	
	@Autowired
	private InvestmentAssembler investmentResourceAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Investment> pageAssembler;
	
	@GetMapping
	@Operation(description = "Get All Investment Details")
	public ResponseEntity<?> getAllInvestments(
			@PageableDefault(
					direction = Direction.DESC,	
					page = 0,
					size = 10					
					)Pageable defaultPage,
			@SortDefault(
					direction = Direction.DESC,
					sort = {"depositedOn"}
					) Sort sort)
	{
		defaultPage.getSortOr(sort);
		Page<Investment> investments = investmentService.getInvestmentDetails(defaultPage);
		return ResponseEntity.ok(pageAssembler.toModel(investments, investmentResourceAssembler));
		
	}
	@GetMapping(path  = "/{id}")	
	public ResponseEntity<?> getInvestmentDteails(@PathVariable Long id)
	{
		Optional<Investment> investmentO=investmentService.getInvestmentDetailsById(id);
		if(investmentO.isPresent())
			return ResponseEntity.ok(investmentResourceAssembler.toModel(investmentO.get()));
		else
		{
			Problem problem = Problem.create() 
					.withDetail("Investment Details for "+id+" not found")
					.withTitle("Not Found")
					.withStatus(HttpStatus.NO_CONTENT)
					.withProperties(map->{
						map.put("id", id);
					});
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
		}
	}
	
	@PostMapping
	@Operation(description = "Create New",requestBody = @RequestBody(ref = "za1975.equity.invest.model.InvestmentModel"))
	public ResponseEntity<?> addInvestmentDetails(@Validated InvestmentModel investmentModel,@HeaderParam("x-auth-token")String token)
	{
		Investment investment=investmentResourceAssembler.toEntity(investmentModel,token);
		investment=investmentService.createNew(investment);
		return ResponseEntity.created(WebMvcLinkBuilder
				.linkTo(InvestmentController.class)
				.slash(investment.getId())
				.toUri())
				.build();
	}
	
	
	
}
