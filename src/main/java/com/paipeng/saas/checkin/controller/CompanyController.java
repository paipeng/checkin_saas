package com.paipeng.saas.checkin.controller;

import com.paipeng.saas.checkin.tenant.entity.Company;
import com.paipeng.saas.checkin.tenant.service.CompanyService;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController extends BaseController{
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Company query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Company company = companyService.query(id);
        if (company == null) {
            throw new SC_NOT_FOUND();
        }
        return company;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Company> query() throws Exception {
        logger.trace("query all");
        return companyService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Company save(@NotNull @RequestBody Company company) throws Exception {
        logger.trace("save: " + company);
        //response.setStatus(SC_CREATED);
        return companyService.save(company);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Company update(@PathVariable("id") Long id, @NotNull @RequestBody Company company) throws Exception {
        logger.trace("update: " + company);
        //response.setStatus(SC_OK);
        return companyService.update(id, company);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        companyService.delete(id);
    }
}
