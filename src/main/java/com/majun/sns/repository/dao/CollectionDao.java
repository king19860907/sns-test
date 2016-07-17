package com.majun.sns.repository.dao;

import com.majun.sns.dto.PostType;
import com.majun.sns.dto.Result;
import com.majun.sns.model.Collection;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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

    public Result<Collection> queryCollections(Long memberId, PostType type, int pageNum, int pageSize){

        Query query = new BasicQuery("{memberId:"+memberId+",type:"+type+"}");

        Result<Collection> result = new Result<Collection>();
        result.setTotalCount(collectionMongoTemplate.count(query,Collection.class));

        query.skip((pageNum-1)*pageSize).limit(pageSize);
        query.with(new Sort(Sort.Direction.ASC,"createTime"));
        result.setResult(collectionMongoTemplate.find(query,Collection.class));
        return result;
    }

    public void setCollectionMongoTemplate(MongoTemplate collectionMongoTemplate) {
        this.collectionMongoTemplate = collectionMongoTemplate;
    }
}
