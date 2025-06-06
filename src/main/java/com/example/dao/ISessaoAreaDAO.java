package com.example.dao;

public interface ISessaoAreaDAO {
    void vincularAreaASessao(int sessaoId, int areaId) throws Exception;
}