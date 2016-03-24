package com.btcc;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.*;
import quickfix.field.OrdType;
import quickfix.field.Side;
import quickfix.field.SubscriptionRequestType;
import quickfix.fix44.Heartbeat;
import quickfix.fix44.Logon;

public class FixApplication implements quickfix.Application {
	//if you request marketdata you can set PARTNER and SECRET_KEY as "1"
	public static final String PARTNER = "your partner ";
	public static final String SECRET_KEY = "your securityKey";

	private Message messageOnLogon;
	private static final Logger log = LoggerFactory.getLogger(FixApplication.class);
	
	public void fromAdmin(quickfix.Message msg, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		String msgType = msg.getHeader().getString(35);
		if((!msgType.equals(Logon.MSGTYPE)) && (!msgType.equals(Heartbeat.MSGTYPE))){
			log.info("receivedType:" + msgType);
			log.info(sessionID+"------ fromAdmin--------"+msg.toString());
		}
	}

	boolean started;

	public void fromApp(quickfix.Message msg, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

		String msgType = msg.getHeader().getString(35);
		if((!msgType.equals(Logon.MSGTYPE)) && (!msgType.equals(Heartbeat.MSGTYPE))){
			log.info("receivedType:" + msgType);
			log.info("        " + sessionID + "------ fromApp---------" + msg.toString());

			String[] fds = msg.toString().split("\u0001");
			for(String fd : fds)
			{
				log.info(fd);
			}
		}

		//Load Test Start

//		if(msgType.equals("U2001") && !started)
//		{
//			started = true;
//			Thread stressTestThread = new Thread(()->{
//				while (true)
//				{
//					String accountString = Config.getInstance().getAccount();
//					try {
//						Message msg1 = MessageProvider.createMarketDataRequest("XBTCNY", SubscriptionRequestType.SNAPSHOT, UUID.randomUUID().toString());
//						if(!Session.sendToTarget(msg1, sessionID))
//						{
//							System.out.println();
//							return;
//						}
//
//						msg1 = MessageProvider.createAccountInfoRequest(accountString, UUID.randomUUID().toString());
//						if(!Session.sendToTarget(msg1, sessionID))
//						{
//							System.out.println();
//							return;
//						}
//
//						msg1 = MessageProvider.createOrderMassStatus2Request(accountString, "XBTCNY", UUID.randomUUID().toString());
//						if(!Session.sendToTarget(msg1, sessionID))
//						{
//							System.out.println();
//							return;
//						}
//
//						Thread.sleep(20);
//					} catch (SessionNotFound sessionNotFound) {
//						sessionNotFound.printStackTrace();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
//			stressTestThread.start();
//		}else if(msgType.equals("j")) {
//			System.out.println(msg);
//		}

//		Load Test
	}

	public void onCreate(SessionID sessionID) {
		try {
			//there should invoke reset()
			Session.lookupSession(sessionID).reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		quickfix.Message message = OKMarketDataRequest.create24HTickerRequest();
//		Session.lookupSession(sessionID).send(message);
		log.info(sessionID+"------ onCreate Session-------"+sessionID);
	
	}
//
	public void onLogon(final SessionID sessionID) {

		if(messageOnLogon != null)
		{
			new Thread(()->{
				try {
					Session.sendToTarget(messageOnLogon, sessionID);
				} catch (SessionNotFound sessionNotFound) {
					sessionNotFound.printStackTrace();
				}
			}).start();
		}

		log.info(sessionID+"------ onLogon-------"+sessionID);
	}

//	public void onLogon(final SessionID sessionID) {
//
//		if(messageOnLogon != null)
//		{
//			new Thread(()->{
//				try {
//					while (true)
//					{
//
//						String accountString = Config.getInstance().getAccount();
//						String clordid = UUID.randomUUID().toString();
////						messageOnLogon=MessageProvider.createAccountInfoRequest(accountString, "req2");
////                        messageOnLogon = MessageProvider.createMarketDataRequest("XBTCNY", SubscriptionRequestType.SNAPSHOT, "req111253");
////						messageOnLogon = MessageProvider.createAccountInfoRequest(accountString, "req12311");
//						messageOnLogon = MessageProvider.createNewOrderSingle(accountString, clordid, Side.BUY, OrdType.LIMIT, 2806, 1, "XBTCNY", '0');
////                      messageOnLogon = MessageProvider.createOrderMassStatusRequest(accountString, "XBTCNY", "reqID998");
////						messageOnLogon = MessageProvider.createOrderMassStatus2Request(accountString, "XBTCNY", "reqIDJ7");
//                        log.info("clordid id {}", clordid);
//						Session.sendToTarget(messageOnLogon, sessionID);
//
//
//						try {
//							Thread.sleep(20);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
////                    Session.sendToTarget(messageOnLogon, sessionID);
//				} catch (SessionNotFound sessionNotFound) {
//					sessionNotFound.printStackTrace();
//				}
//			}).start();
//		}
//
//		log.info(sessionID+"------ onLogon-------"+sessionID);
//	}

	public void onLogout(SessionID sessionID) {
		log.info(sessionID+"------ onLogout -------"+sessionID);
	}

	public void toAdmin(quickfix.Message msg, SessionID sessionID) {
//		msg.setField(new StringField(553, PARTNER));
//		msg.setField(new StringField(554, SECRET_KEY));
		log.info(sessionID+"------ toAdmin---------"+msg.toString());
	}

	public void toApp(quickfix.Message msg, SessionID sessionID) throws DoNotSend {
		log.info("        " + sessionID+"------ toApp-----------"+msg.toString());
	}

	void testMarketMessage(final SessionID sessionID)
	{
//		quickfix.Message message;
//		message = BTCCMarketDataRequest.createIncrementalTickerRequest("XBTCQ5");
//		Session.lookupSession(sessionID).send(message);
	}
	
	private void runMethod(final SessionID sessionID) {
		testMarketMessage(sessionID);
	}

	public void setMessageOnLogon(Message messageOnLogon)
	{
		this.messageOnLogon = messageOnLogon;
	}
}
