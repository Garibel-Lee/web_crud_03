package com._520it.smis.handler;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
	//处理结果集
	T handle(ResultSet rs) throws Exception;
}
