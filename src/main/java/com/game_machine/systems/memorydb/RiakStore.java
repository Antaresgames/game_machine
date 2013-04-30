package com.game_machine.systems.memorydb;

import akka.actor.UntypedActor;

import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.cap.UnresolvedConflictException;
import com.basho.riak.client.convert.ConversionException;
import com.game_machine.core.net.clients.Riak;

public class RiakStore extends UntypedActor {

	private Bucket bucket;

	public RiakStore() {
		String bucketName = "test";
		try {
			bucket = Riak.getClient().createBucket(bucketName).execute();
			bucket = Riak.getClient().fetchBucket(bucketName).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onReceive(Object message) {
		if (message instanceof GameObject) {
			GameObject gameObject = (GameObject) message;
			try {
				bucket.store(gameObject.getId(), gameObject).execute();
			} catch (RiakRetryFailedException | UnresolvedConflictException | ConversionException e) {
				e.printStackTrace();
			}
			
		} else {
			unhandled(message);
		}
	}
}
