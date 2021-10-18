/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koona.tennis.core;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Steve KOUNA
 */
public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDtaSourceInstance() {
        if (singleDataSource == null) {
            singleDataSource = new BasicDataSource();
            singleDataSource.setInitialSize(5);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis");
            singleDataSource.setUsername("root");
            singleDataSource.setPassword("");
        }
        
        return singleDataSource;
    }
}
