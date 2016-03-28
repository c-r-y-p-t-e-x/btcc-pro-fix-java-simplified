package com.btcc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.stage.Stage;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.field.OrdType;
import quickfix.field.Side;
import quickfix.field.SubscriptionRequestType;

public class App
{
    public static void main( String[] args ) throws ConfigError, FileNotFoundException {
		String accountString = Config.getInstance().getAccount();
		/*
		There are 7 requests below.You can only send one request one time. Just comment other requests.
		1st request is used to get market data.
		2nd request is used to get account info.
		3rd request is used to send orders.
		4th request is used to cancel order.
		5th request is Order Status request.
		6th request is used to get mass orders' status.The default time is 24h.You need to set the time in MessageProvider.java.
		7th request is Cancel/Replace request.
		 */

		testMessage(MessageProvider.createMarketDataRequest("XBTCNY", SubscriptionRequestType.SNAPSHOT_PLUS_UPDATES, "req114"));
//		testMessage(MessageProvider.createAccountInfoRequest(accountString, "request001"));
//	    testMessage(MessageProvider.createNewOrderSingle(accountString, "2016031723", Side.SELL, OrdType.LIMIT,3000, 1 , "XBTCNY" , '0'));
//		testMessage(MessageProvider.createOrderCancelRequest(accountString, "clOrdID123d", "XBTCNY", "2d87530a4e014ec0aa011fba8f4a9d10"));
//		testMessage(MessageProvider.createOrderStatusRequest(accountString, "XBTCNY", "ccdb61470f7e4fb4902ce68174a4858f", "reqID126"));
//		testMessage(MessageProvider.createOrderMassStatus2Request(accountString, "XBTCNY", "reqIDJ7"));
//		testMessage(MessageProvider.createOrderCancelReplaceRequest(accountString , "XBTCNY","2016032208","d71f75b633834cca874d3e379609f5c5",2998,1));

	}

	static void testMessage(quickfix.fix44.Message messageOnLogon) throws ConfigError, FileNotFoundException {
		FixApplication fixapplication = new FixApplication();
		fixapplication.setMessageOnLogon(messageOnLogon);
		InputStream inputStream = new FileInputStream("quickfix-client.properties");
		SessionSettings settings = new SessionSettings(inputStream);
		MessageStoreFactory storeFactory = new FileStoreFactory(settings);
		LogFactory logFactory = new FileLogFactory(settings);
		MessageFactory messageFactory = new DefaultMessageFactory();
		Initiator initiator = new SocketInitiator(fixapplication, storeFactory, settings, logFactory, messageFactory);
		initiator.block();

	}
}
