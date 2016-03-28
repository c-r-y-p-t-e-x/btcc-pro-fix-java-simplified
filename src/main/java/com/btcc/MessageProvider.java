package com.btcc;

import com.btcc.fix.message.AccReqID;
import com.btcc.fix.message.AccountInfoRequest;
import quickfix.Group;
import quickfix.StringField;
import quickfix.UtcTimeStampField;
import quickfix.field.*;
import quickfix.fix44.*;

import java.util.Date;

/**
 * Created by zhenning on 15/9/6.
 */
public class MessageProvider {

    public static AccountInfoRequest createAccountInfoRequest(String account, String accReqID) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest();

        accountInfoRequest.set(new Account(account));
        accountInfoRequest.set(new AccReqID(accReqID));
        return accountInfoRequest;
    }


    public static MarketDataRequest createMarketDataRequest(String symbol, char subscriptionRequestType, String mDReqID) {
        MarketDataRequest tickerRequest = new MarketDataRequest();

        MarketDataRequest.NoRelatedSym noRelatedSym = new MarketDataRequest.NoRelatedSym();
        noRelatedSym.set(new Symbol(symbol));
        tickerRequest.addGroup(noRelatedSym);

        tickerRequest.set(new MDReqID(mDReqID));
        tickerRequest.set(new SubscriptionRequestType(subscriptionRequestType));
        tickerRequest.set(new MarketDepth(5));
//
        addMDType(tickerRequest, MDEntryType.BID);
        addMDType(tickerRequest, MDEntryType.OFFER);
//        addMDType(tickerRequest, MDEntryType.TRADE);
//        addMDType(tickerRequest, MDEntryType.INDEX_VALUE);
//        addMDType(tickerRequest, MDEntryType.OPENING_PRICE);
//        addMDType(tickerRequest, MDEntryType.CLOSING_PRICE);
//        addMDType(tickerRequest, MDEntryType.SETTLEMENT_PRICE);
//        addMDType(tickerRequest, MDEntryType.TRADING_SESSION_HIGH_PRICE);
//        addMDType(tickerRequest, MDEntryType.TRADING_SESSION_LOW_PRICE);
//        addMDType(tickerRequest, MDEntryType.TRADING_SESSION_VWAP_PRICE);
//        addMDType(tickerRequest, MDEntryType.IMBALANCE);
//        addMDType(tickerRequest, MDEntryType.TRADE_VOLUME);
//        addMDType(tickerRequest, MDEntryType.OPEN_INTEREST);

        return tickerRequest;
    }

    private static void addMDType(MarketDataRequest tickerRequest, char type) {
        MarketDataRequest.NoMDEntryTypes g0 = new MarketDataRequest.NoMDEntryTypes();
        g0.set(new MDEntryType(type));
        tickerRequest.addGroup(g0);
    }

    public static NewOrderSingle createNewOrderSingle(String account, String clOrdID, char side, char ordertype, double price, double amount, String symbol, char timeInForce)
    {
        NewOrderSingle newOrderSingleRequest = new NewOrderSingle();
        newOrderSingleRequest.set(new Account(account));
        newOrderSingleRequest.set(new ClOrdID(clOrdID));
        newOrderSingleRequest.set(new Side(side));
        newOrderSingleRequest.set(new Symbol(symbol));
        newOrderSingleRequest.set(new OrdType(ordertype));
        if(ordertype == OrdType.STOP){
            newOrderSingleRequest.set(new StopPx(price));
        }else if(ordertype == OrdType.LIMIT){
            newOrderSingleRequest.set(new Price(price));
        }
        newOrderSingleRequest.set(new OrderQty(amount));
        newOrderSingleRequest.set(new TransactTime());
        newOrderSingleRequest.set(new TimeInForce(timeInForce));
        return newOrderSingleRequest;
    }

    public static OrderCancelRequest createOrderCancelRequest(String account, String clOrdID, String symbol, String orderid) {
        OrderCancelRequest orderCancelRequest = new OrderCancelRequest();

        orderCancelRequest.set(new ClOrdID(clOrdID));
        orderCancelRequest.set(new Side('1'));//必填，但无意义

        orderCancelRequest.set(new Account(account));
        orderCancelRequest.set(new Symbol(symbol));
        orderCancelRequest.set(new OrderID(orderid));//订单编号
        orderCancelRequest.set(new TransactTime(new Date()));
        return orderCancelRequest;
    }


    public static Message createOrderMassStatus2Request(String account, String symbol, String reqID) {
        Message orderMassStatus2Request = new Message();
        orderMassStatus2Request.getHeader().setField(new MsgType("AF2"));
        orderMassStatus2Request.setField(new Account(account));
        orderMassStatus2Request.setField(new Symbol(symbol));
        orderMassStatus2Request.setField(new MassStatusReqID(reqID));

        UtcTimeStampField startTime = new UtcTimeStampField(9010);

        startTime.setValue(new Date(0));
//        startTime.setValue(new Date(115, 9, 11));
        orderMassStatus2Request.setField(startTime);

        UtcTimeStampField endTime = new UtcTimeStampField(9011);
//
        endTime.setValue(new Date(116,03,27));
        orderMassStatus2Request.setField(endTime);



//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.PENDING_NEW));
        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.NEW));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.FILLED));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.DONE_FOR_DAY));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.CANCELED));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.PENDING_CANCEL));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus(OrdStatus.PARTIALLY_FILLED));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus('S'));
//        addOrdStatus(orderMassStatus2Request, new OrdStatus('G'));

        return orderMassStatus2Request;
    }

    private static void addOrdStatus(Message orderMassStatus2Request, OrdStatus field2) {
        Group ordStatusGroup1 = new Group(9020, 39, new int[]{39, 0});
        ordStatusGroup1.setField(field2);
        orderMassStatus2Request.addGroup(ordStatusGroup1);
    }

    public static OrderStatusRequest createOrderStatusRequest(String account, String symbol, String orderId, String clOrderId) {
        OrderStatusRequest orderStatusRequest = new OrderStatusRequest();
        orderStatusRequest.set(new Account(account));
        orderStatusRequest.set(new Symbol(symbol));
        orderStatusRequest.set(new OrderID(orderId));//订单编号
        orderStatusRequest.set(new ClOrdID(clOrderId));
        orderStatusRequest.set(new Side(Side.BUY));//必填，但无意义
        return orderStatusRequest;
    }
    public static OrderCancelReplaceRequest createOrderCancelReplaceRequest(String account, String symbol,String clOrderId, String orderid,double price ,double amount) {
        OrderCancelReplaceRequest orderCancelReplaceRequest = new quickfix.fix44.OrderCancelReplaceRequest();

        orderCancelReplaceRequest.set(new ClOrdID(clOrderId));
        orderCancelReplaceRequest.set(new Account(account));
        orderCancelReplaceRequest.set(new Symbol(symbol));
        orderCancelReplaceRequest.set(new OrderID(orderid));
        orderCancelReplaceRequest.set(new Price(price));
        orderCancelReplaceRequest.set(new OrderQty(amount));
        return orderCancelReplaceRequest;
    }

}
