package com.majun.sns.repository.dao;

import com.majun.sns.model.Collection;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by jun_ma on 2016/7/15.
 */
public class CollectionDao {

    private MongoTemplate collectionMongoTemplate;

    public void saveCollection(Collection collection){

        collectionMongoTemplate.save(collection);

    }

    public void setCollectionMongoTemplate(MongoTemplate collectionMongoTemplate) {
        this.collectionMongoTemplate = collectionMongoTemplate;
    }
}
