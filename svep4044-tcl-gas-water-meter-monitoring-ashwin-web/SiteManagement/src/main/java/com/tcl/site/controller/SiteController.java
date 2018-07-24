/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tcl.site.dto.SiteCreateRequestDto;
import com.tcl.site.dto.SiteCreateResponseDto;
import com.tcl.site.dto.SiteDeleteRequestDto;
import com.tcl.site.dto.SiteResponseDto;
import com.tcl.site.dto.SiteStatusReqDto;
import com.tcl.site.dto.SiteUpdateRequestDto;
import com.tcl.site.response.ListResponse;
import com.tcl.site.response.Response;
import com.tcl.site.service.SiteService;

@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "TestSuccess";
	}

	@PostMapping
	public @ResponseBody Response<SiteCreateResponseDto> createSite(@Valid @RequestBody SiteCreateRequestDto requestDto,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return siteService.createSite(requestDto);
	}

	@PutMapping
	public @ResponseBody Response<String> updateSite(@Valid @RequestBody SiteUpdateRequestDto requestDto) {
		return siteService.updateSite(requestDto);
	}

	@DeleteMapping
	public @ResponseBody Response<Boolean> deleteSite(@Valid @RequestBody SiteDeleteRequestDto requestDto) {
		return siteService.deleteSite(requestDto);
	}

	@GetMapping
	public @ResponseBody ListResponse<List<SiteResponseDto>> listSites(@RequestParam String userId,
			@RequestParam(required = false) String search, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return siteService.listSites(userId, search, page, size);
	}

	@GetMapping("/profile/{siteId}")
	public @ResponseBody Response<SiteResponseDto> getSiteDetails(@PathVariable("siteId") String siteId) {
		return siteService.siteDetails(siteId);

	}

	@PutMapping("/status")
	public @ResponseBody Response<Boolean> updateSiteStatus(@Valid @RequestBody SiteStatusReqDto requestDto,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return siteService.updateSiteStatus(requestDto);
	}
	
	@GetMapping("/list")
	public @ResponseBody ListResponse<List<SiteResponseDto>> filterSiteList(@RequestParam(required = false) List<String> filters,@RequestParam(required = false) String sortByFieldName,@RequestParam(required = false) String sortType, @RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestParam(required = false)  String userId) {
		return siteService.filterSiteList(filters,sortByFieldName,sortType, page, size,userId);
	}

}
