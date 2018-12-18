package api;

import osc.*;

import javax.swing.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class OSCController {

    private OSCPortOut oscPort;
    private String address = "192.168.1.82";

    public OSCController() {
        try {
            oscPort = new OSCPortOut();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        addressChanged();
    }

    public void addressChanged() {
        // the variable OSCPortOut tries to get an instance of OSCPortOut
        // at the address indicated by the addressWidget
        try {
            oscPort = new OSCPortOut(InetAddress.getByName(address));
            // if the oscPort variable fails to be instantiated then sent
            // the error message
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSendOn(String ruta, String value) {
        // if "Set Address" has not been performed then give the message to set
        // it first
        if (null == oscPort) {
            System.out.println("Please set an address first");
        }

        // send an OSC message to start the synth "pink" on node 1000.
        List<Object> args = new ArrayList<Object>(6);
        args.add(value);
        //args.add(new Integer(node));
        //args.add(new Integer(1));
        //args.add(new Integer(0));
        //args.add("freq");
        //args.add(new Float(freq));
        // a comma is placed after /s_new in the code
        OSCMessage msg = new OSCMessage("/"+ruta, args);

        // Object[] args2 = {new Symbol("amp"), new Float(0.5)};
        // OscMessage msg2 = new OscMessage("/n_set", args2);
        //oscPort.send(msg);

        // try to use the send method of oscPort using the msg in nodeWidget
        // send an error message if this doesn't happen
        try {
            oscPort.send(msg);
        } catch (Exception ex) {
            System.out.println("Couldn't send");
        }
    }
}
