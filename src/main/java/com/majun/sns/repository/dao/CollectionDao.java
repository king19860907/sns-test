package com.majun.sns.repository.dao;

import com.majun.sns.model.Collection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by jun_ma on 2016/7/15.
 */
public class CollectionDao {

    private MongoTemplate collectionMongoTemplate;

    public void saveCollection(Collection collection){
        collectionMongoTemplate.save(collection);
    }

    public void removeCollection(Long memberId,List<ObjectId> ids){
        collectionMongoTemplate.remove(Query.query(Criteria.where("memberId").is(memberId).and("_id").in(ids)),Collection.class);
    }

    public void setCollectionMongoTemplate(MongoTemplate collectionMongoTemplate) {
        this.collectionMongoTemplate = collectionMongoTemplate;
    }
}
