package uz.pdp.todo.service;

import uz.pdp.todo.mapper.BaseMapper;
import uz.pdp.todo.validator.BaseValidator;

public abstract class AbstractService<R, M extends BaseMapper, V extends BaseValidator> {
    protected final R repository;
    protected final M mapper;
    protected final V validator;

    public AbstractService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }
}
