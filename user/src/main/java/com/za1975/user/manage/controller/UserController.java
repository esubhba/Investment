package com.za1975.user.manage.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.za1975.user.manage.assembler.UserModelAssembler;
import com.za1975.user.manage.entity.User;
import com.za1975.user.manage.models.Credentials;
import com.za1975.user.manage.models.UserModel;
import com.za1975.user.manage.service.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/users")
@RefreshScope
@OpenAPIDefinition(info = @Info(description = "Users Api"),tags = @Tag(name = "User Details"))
public class UserController {
	
	@Autowired
	private UserModelAssembler userModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<User> pagedResourcesAssembler;
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping(path = "/register",consumes = {MediaTypes.HAL_JSON_VALUE})
	@Operation(operationId = "001",description = "Register New User",		
		responses =
			{
					@ApiResponse(responseCode = "201",description = "Created"),
					@ApiResponse(responseCode = "500",description = "Error")
			})
	public ResponseEntity<?> registerUser(@RequestBody UserModel model)
	{
		User entity=userModelAssembler.toEntity(model);
		entity=userService.createUser(entity);
		return ResponseEntity.created(
				WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserController.class, entity.getUid())
				.getDetails(entity.getUid())).toUri()).build();
	}
	
	@GetMapping(path = "/",produces = MediaTypes.HAL_JSON_VALUE)
	@Operation(operationId = "002",description = "Get All Users",	
	responses = 
				{
					@ApiResponse(responseCode  = "200",description = "All User Details",
							content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE)})
				}
	)	
	public ResponseEntity<?> getAllUsers(@PageableDefault(page = 1,size = 10)Pageable pageable)
	{
		Page<User> userP = userService.getAll(pageable);
		return ResponseEntity.ok(pagedResourcesAssembler.toModel(userP, userModelAssembler));
	}
	
	
	@GetMapping(path="/{id}",produces = {MediaTypes.HAL_JSON_VALUE})
	@Operation(operationId = "003",description = "Get Details",	
		responses = 
					{
						@ApiResponse(responseCode  = "200",description = "User Details",
								content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE)})
					}
		,parameters = @Parameter(example = "a3d2bd41-cde6-4499-8dcc-61cf0c44b2e5",
				in = ParameterIn.PATH,name = "id" )
			)
	public ResponseEntity<?> getDetails(@PathVariable("id") String id)
	{
		Optional<User> userO = userService.getUserById(id);
		return ResponseEntity.ok(userModelAssembler.toModel(userO.orElseThrow()));
		
	}
	
	@PutMapping(path="/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaTypes.HAL_JSON_VALUE},produces = MediaTypes.HAL_JSON_VALUE)
	@Operation(operationId = "004",description = "Update Details",	
	responses = 
				{
					@ApiResponse(responseCode  = "200",description = "User Details updated",
							content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE)})
				}
	,parameters = 
			{
				@Parameter(example = "a3d2bd41-cde6-4499-8dcc-61cf0c44b2e5",
						in = ParameterIn.PATH,name = "id",required = true)
				
			} 
		)
	public ResponseEntity<?> updateDetails(@RequestBody UserModel userModel,@PathVariable(name = "id")String id)
	{
		User entity=userModelAssembler.toEntity(userModel);
		entity.setUid(id);
		entity=userService.updateDetails(entity);
		return ResponseEntity.ok(userModel);
	}
	@Operation(operationId = "005",description = "Update Password",	
			responses = 
						{
							@ApiResponse(responseCode  = "200",description = "User Password updated",
									content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE)})
						}
			,parameters = 
					{
						@Parameter(example = "a3d2bd41-cde6-4499-8dcc-61cf0c44b2e5",
								in = ParameterIn.PATH,name = "id",required = true)
						
					} 
	)
	@PatchMapping(path="/{id}",consumes = MediaTypes.HAL_JSON_VALUE,produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> updatePassword(@PathVariable("id")String id,@RequestBody Credentials crd)
	{
		
		Optional<User> entityO=userService.getCredential(crd);
		User entity=entityO.get();
		if(entity.getUid().equals(id))
			entity.setPassword(crd.getPassword());
		userService.updateDetails(entity);
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
				.location(WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.linkTo(UserController.class).slash("login").withRel("login")).toUri())							
				.build();
	}
	
	

}
