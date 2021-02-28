package com.equity.accessors.data.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "shares",itemRelation = "share")
@JsonInclude(content = Include.NON_NULL)
public class ShareModel extends RepresentationModel<ShareModel> {
	private Long id;
	private String details;
	private String code;
	private String exchangeName;
	

}