package com.example.dao;

import java.util.List;

import com.example.model.Area;

public interface IAreaDAO {
    void salvar(Area area) throws Exception;
    List<Area> buscarTodas() throws Exception;
    Area buscarPorId(int id) throws Exception;
}
