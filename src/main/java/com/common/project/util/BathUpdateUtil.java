package com.common.project.util;
//
//import com.mongodb.bulk.BulkWriteResult;
//import org.springframework.dao.InvalidDataAccessApiUsageException;
//import org.springframework.data.mongodb.core.BulkOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.List;
//
///**
// * 批量更新mongodb数据
// * @author afei
// * @date 2018-12-06
// */
//public class BathUpdateUtil {
//
//    public static String bathUpdate(MongoTemplate mongoTemplate, String collName,
//                                    List<BathUpdateOptions> options, boolean ordered) {
//        // BulkMode.UNORDERED:表示并行处理，遇到错误时能继续执行不影响其他操作；BulkMode.ORDERED：表示顺序执行，遇到错误时会停止所有执行
//        return doBathUpdate(mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, collName), collName, options, ordered);
//    }
//
//    public static String bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass,
//                                    List<BathUpdateOptions> options, boolean ordered) {
//        String collectionName = determineCollectionName(entityClass);
//        return doBathUpdate(mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, collectionName),
//                collectionName, options, ordered);
//    }
//
//
//    public static String bathUpdate(MongoTemplate mongoTemplate, String collName,
//                                    List<BathUpdateOptions> options) {
//        return doBathUpdate(mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, collName), collName, options, true);
//    }
//
//    public static String bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass,
//                                    List<BathUpdateOptions> options) {
//        String collectionName = determineCollectionName(entityClass);
//        return doBathUpdate(mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, collectionName),
//                collectionName, options, true);
//    }
//
//    private static String doBathUpdate(BulkOperations ops, String collName,
//                                       List<BathUpdateOptions> options, boolean ordered) {
//        for (BathUpdateOptions option : options) {
//            ops.updateOne(option.getQuery(), option.getUpdate());
//        }
//        BulkWriteResult bulkWriteResult = ops.execute();
//        return bulkWriteResult.toString();
//    }
//
//    private static String determineCollectionName(Class<?> entityClass) {
//        if (entityClass == null) {
//            throw new InvalidDataAccessApiUsageException(
//                    "No class parameter provided, entity collection can't be determined!");
//        }
//        String collName = entityClass.getSimpleName();
//        if(entityClass.isAnnotationPresent(Document.class)) {
//            Document document = entityClass.getAnnotation(Document.class);
//            collName = document.collection();
//        } else {
//            collName = collName.replaceFirst(collName.substring(0, 1)
//                    ,collName.substring(0, 1).toLowerCase()) ;
//        }
//        return collName;
//    }
//
//}
