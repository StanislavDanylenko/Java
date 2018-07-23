package base;

import dbService.dao.UsersDAO;

public interface TResultHandler<T> {
    T handle(UsersDAO session);
}
