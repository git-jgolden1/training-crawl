package com.epicGamecraft.dungeonCrawlDepths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import reactor.core.publisher.Mono;

import com.couchbase.client.java.*;
import com.couchbase.client.java.query.ReactiveQueryResult;

public class CouchbaseVerticle extends AbstractVerticle {

	private static final Logger LOGGER = LoggerFactory.getLogger(CouchbaseVerticle.class);

	
//	private AsyncCluster cluster;
	
//	AsyncCluster cluster = AsyncCluster.create();
	
    @Override
    public void start(Promise<Void> promise) throws Exception {
	    final EventBus eb = vertx.eventBus();
    	final ReactiveCluster connection = ReactiveCluster.connect("localhost:11210", "Administrator", "password");
//    	final ReactiveBucket bucket = connection.bucket("registration");
//    	final ReactiveCollection collection = bucket.defaultCollection();
//      final Mono<ReactiveQueryResult> query = connection.query("select \"Hello World\" as greeting");
//      final ReactiveQueryResult result = query.block();
//      final com.couchbase.client.java.json.JsonObject firstResult = result.rowsAsObject().blockFirst();
//      System.out.println(firstResult.toString());
        eb.consumer("couchbase.query", message -> { message.reply(connection.query((String) message.body()));        	
        }); 
  
    }
    
//	@Override
//	public void init(Vertx vertx, Context context) {
//		super.init(vertx, context);
//		//getting the configuration JSON
//		JsonObject config = context.config();
//	 
//		//getting the bootstrap node, as a JSON array (default to localhost)
//		JsonArray seedNodeArray = config.getJsonArray("couchbase.seedNodes", new JsonArray().add("localhost"));
//		//convert to a List
//		List seedNodes = new ArrayList&lt;&gt;(seedNodeArray.size());
//		for (Object seedNode : seedNodeArray) {
//			seedNodes.add((String) seedNode);
//		}
//		//use that to bootstrap the Cluster
//		this.cluster = CouchbaseAsyncCluster.create(seedNodes);
//	}
//
//	@Override
//	public void stop(Future stopFuture) throws Exception {
//		cluster.disconnect()
//				.doOnNext(isDisconnectedCleanly -&gt; LOGGER.info("Disconnected Cluster (cleaned threads: " + isDisconnectedCleanly + ")"))
//				.subscribe(
//						isDisconnectedCleanly -&gt; stopFuture.complete(),
//						stopFuture::fail,
//						Schedulers::shutdown);
//	}
	
//	@Override
//	public void start(Promise<Void> promise) throws Exception {
//		vertx.eventBus().consumer(BrowserInput.name(), this::handleKey);
//	}

//	private void handleKey(Message<String> message) {
//		LOGGER.debug("Received message: " + message.body());
//	}

}
