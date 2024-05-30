package org.springproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springproject.controller.dto.ExpenseRequest;
import org.springproject.service.model.ExpenseServiceRequest;

@Mapper
public interface ExpenseHandlerMapper {

    ExpenseHandlerMapper INSTANCE = Mappers.getMapper(ExpenseHandlerMapper.class);

    ExpenseServiceRequest handleExpenseRequest(ExpenseRequest expenseRequest);


}
