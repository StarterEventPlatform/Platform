package com.eventplatform.repository;

import com.eventplatform.pojo.klass.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao {
    @Autowired
    private MongoOperations mongoOperations;

    public int getNextSequenceId(String collectionKey) {
        Query query = new Query(Criteria.where("id").is(collectionKey));
        Update update = new Update();
        update.inc("sequence", 1);
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true);
        Sequence sequence = mongoOperations.findAndModify(query, update, findAndModifyOptions, Sequence.class);
        if (sequence == null) {
            sequence = new Sequence();
            sequence.setId(collectionKey);
            mongoOperations.save(sequence);
        }
        return sequence.getSequence();
    }
}
