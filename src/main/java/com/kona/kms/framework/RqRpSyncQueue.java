package com.kona.kms.framework;




public class RqRpSyncQueue
{
	private Object requestQ = null;
	private Object responseQ = null;
	
	public RqRpSyncQueue() {
		requestQ = null;
		responseQ = null;
	}
	
	// request request
	public void requestRq(Object req) {
		if (req == null) {
			req = new String("requested, but null!!!");
		}
		synchronized (this) {
			/* to put request */
			while (requestQ != null) {
				try {
					this.wait(1);						
				}
				catch (Exception e) {
					try { 
						Thread.sleep(1); 
					}
					catch (Exception ne) 
					{ 
						
					}
					continue;
				}				
			}
			/* put request & notify to request-receiver */
			requestQ = req;
			this.notify();
		}
	}
	
	// wait for response
	public Object waitforRp() {
		Object ret = null;
		synchronized (this) {			
			/* to get response */
			while (responseQ == null) {
				try {
					this.wait(1);						
				}
				catch (Exception e) {
					try { Thread.sleep(1); }
					catch (Exception ne) { }
					continue;
				}
			}
			/* get response */
			ret = responseQ;
			responseQ = null;
			this.notify();
		}
		return ret;
	}

	// reply response
	public void replyRp(Object resp) {
		if (resp == null) {
			resp = new String("returned, but null!!!");
		}
		synchronized (this) {
			/* to put request */
			while (responseQ != null) {
				try {
					this.wait(1);						
				}
				catch (Exception e) {
					try { Thread.sleep(1); }
					catch (Exception ne) { }
					continue;
				}				
			}
			/* put request & notify to request-receiver */
			responseQ = resp;
			this.notify();
		}
	}
	
	// wait for request
	public Object waitforRq() {
		Object ret = null;
		synchronized (this) {			
			/* to get response */
			while (requestQ == null) {
				try {
					this.wait(1);						
				}
				catch (Exception e) {
					try { Thread.sleep(1); }
					catch (Exception ne) { }
					continue;
				}
			}
			/* get response */
			ret = requestQ;
			requestQ = null;
			this.notify();
		}
		return ret;
	}
	
	// request and then wait for response
	public Object requestNwaitforRp(Object req) {
		requestRq(req);
		return waitforRp();
	}
	
	// reply response and then wait for next request
	public Object replyNwaitforRq(Object resp) {
		replyRp(resp);
		return waitforRq();
	}

}



