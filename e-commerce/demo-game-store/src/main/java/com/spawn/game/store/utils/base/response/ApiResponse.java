package com.spawn.game.store.utils.base.response;

import lombok.Data;

@Data
public class ApiResponse {

    private GameStoreResponse payload;
    private ExceptionResponse errors;

}
