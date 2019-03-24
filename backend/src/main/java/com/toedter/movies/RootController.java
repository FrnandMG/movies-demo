/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toedter.movies;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Greg Turnquist
 */
@RestController
class RootController {

	@GetMapping("/")
    ResponseEntity<RepresentationModel> root() {

		RepresentationModel resourceSupport = new RepresentationModel();

		resourceSupport.add(linkTo(methodOn(RootController.class).root()).withSelfRel());

		Link selfLink = linkTo(MovieController.class).slash("movies").withRel("movies");
		Link templatedLink = new Link(selfLink.getHref() + "{?size,page}").withRel("movies");

		resourceSupport.add(templatedLink);

		return ResponseEntity.ok(resourceSupport);
	}

}
