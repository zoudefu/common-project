package com.common.project.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author weiju.xi
 * @Description clickhouse连接工具类
 * @Date 17:29 2021/7/11
 * @Param
 * @return
 **/
@Slf4j
public class ClickHouseConfig {

    private static String clickhouseAddress;
    private static String clickhouseUsername;
    private static String clickhousePassword;
    private static String clickhouseDB;
    private static Integer clickhouseSocketTimeout;
    private static Integer clickhouseConnectionTimeOut;

    public void setClickhouseAddress(String address) {
        ClickHouseConfig.clickhouseAddress = address;
    }

    public void setClickhouseUsername(String username) {
        ClickHouseConfig.clickhouseUsername = username;
    }

    public void setClickhousePassword(String password) {
        ClickHouseConfig.clickhousePassword = password;
    }

    public void setClickhouseDB(String db) {
        ClickHouseConfig.clickhouseDB = db;
    }

    public void setClickhouseSocketTimeout(Integer socketTimeout) {
        ClickHouseConfig.clickhouseSocketTimeout = socketTimeout;
    }

    public void setClickhouseConnectionTimeOut(Integer connectionTimeOut) {
        ClickHouseConfig.clickhouseConnectionTimeOut = connectionTimeOut;
    }

    static {
        log.info("==== 开始初始化CK配置...");
        try {
            ClassPathResource resourceMain = new ClassPathResource("application.properties");
            Properties propertiesMain = PropertiesLoaderUtils.loadProperties(resourceMain);
            String active = propertiesMain.getProperty("spring.profiles.active");
            if (StringUtils.isEmpty(active)) {
                clickhouseAddress = propertiesMain.getProperty("spring.clickhouse.address");
                clickhouseUsername = propertiesMain.getProperty("spring.clickhouse.username");
                clickhousePassword = propertiesMain.getProperty("spring.clickhouse.password");
                clickhouseDB = propertiesMain.getProperty("spring.clickhouse.db");
                clickhouseSocketTimeout = Integer.valueOf(propertiesMain.getProperty("spring.clickhouse.socketTimeout"));
                clickhouseConnectionTimeOut = Integer.valueOf(propertiesMain.getProperty("spring.clickhouse.connectionTimeout"));
            } else {
                ClassPathResource resource = new ClassPathResource("application-" + active + ".properties");
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                clickhouseAddress = properties.getProperty("spring.clickhouse.address");
                clickhouseUsername = properties.getProperty("spring.clickhouse.username");
                clickhousePassword = properties.getProperty("spring.clickhouse.password");
                clickhouseDB = properties.getProperty("spring.clickhouse.db");
                clickhouseSocketTimeout = Integer.valueOf(properties.getProperty("spring.clickhouse.socketTimeout"));
                clickhouseConnectionTimeOut = Integer.valueOf(properties.getProperty("spring.clickhouse.connectionTimeout"));
            }
        } catch (Exception e) {
            log.error("==== 初始化CK发生异常");
        }
    }

    public static Connection getConn() {
        log.info("=====初始化ClickHouseProperties开始=====");
        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(clickhouseUsername);
        properties.setPassword(clickhousePassword);
        properties.setDatabase(clickhouseDB);
        properties.setSocketTimeout(clickhouseSocketTimeout);
        properties.setConnectionTimeout(clickhouseConnectionTimeOut);
        String[] url = clickhouseAddress.split(",");
        for (int i = 0; i < url.length; i++) {
            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(url[i], properties);
            try {
                conn = clickHouseDataSource.getConnection();
                log.info("=====初始化ClickHouseProperties完成=====" + conn);
                return conn;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("getConn exception{}", e.getMessage());
            }
        }
        return null;
    }

    public static List<Map> exeSql(String sql) {
        Connection connection = getConn();
        Statement statement = null;
        try {
            log.info("【ClickHouseConfig】exeSql:{}", sql);
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while (results.next()) {
                Map row = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
                }
                list.add(row);
            }
            return list;
        } catch (SQLException e) {
            log.error("ExeSql:{}", sql);
            e.printStackTrace();
        } finally {
            close(null, statement, connection);
        }
        return null;
    }

    public static Map<String, Object> exeSqlMap(String sql) {
        Connection connection = getConn();
        Statement statement = null;
        try {
            log.info("exeSqlMap:{}", sql);
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            Map<String, Object> row = new HashMap<>();
            if (results.next()) {
                row.put(rsmd.getColumnName(1), results.getString(rsmd.getColumnName(1)));
            }
            return row;
        } catch (SQLException e) {
            log.error("exeSqlMap:{}", sql);
            e.printStackTrace();
        } finally {
            close(null, statement, connection);
        }
        return null;
    }


    public static List<Map> exeSqlObject(String sql) {
        Connection connection = getConn();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            List<Map> list = new ArrayList();
            if (results != null) {
                ResultSetMetaData rsmd = results.getMetaData();

                while (results.next()) {
                    Map row = new HashMap();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        row.put(rsmd.getColumnName(i), results.getObject(rsmd.getColumnName(i)));
                    }
                    list.add(row);
                }
            } else {
                list = null;
            }
            return list;
        } catch (SQLException e) {
            log.error("ExeSql:{}", sql);
            e.printStackTrace();
        } finally {
            close(null, statement, connection);
        }
        return null;
    }


    public static boolean execute(String sql) throws Exception {
        Connection connection = getConn();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            log.error("ExeSql:{}", sql);
            e.printStackTrace();
        } finally {
            close(null, statement, connection);
        }
        return false;

    }

    public static List<Integer> batchExecute(String sql, List<List<Object>> valueLists) throws Exception {
        Connection conn = getConn();
        PreparedStatement preStatement = null;
        StopWatch st = new StopWatch();
        st.start("batchExecute");
        log.info("==== 预处理方式批量执行,sql={}", sql);
        if (CollectionUtils.isEmpty(valueLists)) {
            log.error("==== 批处理数据量为0");
            return Lists.newArrayList();
        }
        try {
            conn.setAutoCommit(false);
            preStatement = conn.prepareStatement(sql);
            for (List<Object> valueList : valueLists) {
                for (int i = 0; i < valueList.size(); i++) {
                    preStatement.setObject(i + 1, valueList.get(i));
                }
                preStatement.addBatch();
            }
            int[] result = preStatement.executeBatch();
            preStatement.clearBatch();
            conn.commit();
            return Arrays.stream(result).boxed().collect(Collectors.toList());
        } catch (Exception e) {
            log.error("==== 批量执行SQL异常：", e);
            throw e;
        } finally {
            st.stop();
            log.info("==== 批处理量：{}，总耗时：{}ms", valueLists.size(), st.getTotalTimeMillis());
            close(null, preStatement, conn);
        }
    }

    public static void close(ResultSet rs, Statement stm, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            log.error("==== 关闭连接异常：", e);
        }
    }


}
