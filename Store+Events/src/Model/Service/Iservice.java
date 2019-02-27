/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Service;

import java.util.List;

/**
 *
 * @author asus
 */
public interface Iservice<T> {
    void insert(T t );
    void delete(int a) ;
    void update(T t,int a ) ;
    List<T> GetAll() ;
  //  T get(T t) ;
}
