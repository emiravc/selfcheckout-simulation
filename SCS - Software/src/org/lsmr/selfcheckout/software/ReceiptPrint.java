package org.lsmr.selfcheckout.software;

import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.software.Listeners.ReceiptPrintListener;

public class ReceiptPrint {

    public ReceiptPrinterListenerInterface printerListener;
    private SelfCheckoutStation scs;
    private int inkAmount;
    private int paperAmount;

    private boolean lowAmountPaper;
    private boolean lowAmountInk;

    private static final int MAXIMUM_INK = 1 << 20;
    private static final int MAXIMUM_PAPER = 1 << 10;

    public String removePrintedreceipt() {
        return scs.printer.removeReceipt();
    }

    public void detectLowOnInk(int inkLevel) {
        int lowPercentageInk = MAXIMUM_INK % 10;

        if (inkLevel < lowPercentageInk) {
            this.lowAmountInk = true;
            System.out.println("The ink amount is 10%, please refill.");
        }
    }

    public void detectLowOnPaper(int paperLevel) {
        int lowPercentagePaper = MAXIMUM_PAPER % 10;

        if (paperLevel < lowPercentagePaper) {
            this.lowAmountPaper = true;
            System.out.println("The paper amount is 10%, please refill.");
        }
    }

    public void addingInk(int ink) {
        scs.printer.addInk(ink);
        promptInkAdded(ink);
    }

    public void addingPaper(int paper) {
        scs.printer.addPaper(paper);
        promptPaperAdded(paper);
    }

    public void promptPaperAdded(int paperAdded) {
        if (getaddPaper() == true) {
            this.paperAmount += paperAdded;
            setaddPaper(true);
        }
    }

    public void promptInkAdded(int inkAdded) {
        if (getaddInk() == true) {
            this.inkAmount += inkAdded;
            setaddInk(false);
        }
    }

    // PAPER GETTERS AND SETTERS
    public void setpaperAmount(int paperAmount) {
        this.paperAmount = paperAmount;
    }

    public void setaddPaper(boolean state) {
        this.printerListener.addPaper = state;
    }

    public boolean getlowAmountPaper() {
        return this.lowAmountPaper;
    }

    public int getpaperAmount() {
        return this.paperAmount;
    }

    public boolean getaddPaper() {
        return this.printerListener.addPaper;
    }

    public boolean getNoPaper() {
        return this.printerListener.noPaper;
    }

    // INK GETTERS AND SETTERS
    public void setinkAmount(int inkAmount) {
        this.inkAmount = inkAmount;
    }

    public void setaddInk(boolean state) {
        this.printerListener.addInk = state;
    }

    public boolean getlowAmountInk() {
        return this.lowAmountInk;
    }

    public int getinkAmount() {
        return this.inkAmount;
    }

    public boolean getaddInk() {
        return this.printerListener.addInk;
    }

    public boolean getNoInk() {
        return this.printerListener.noInk;
    }

    public class ReceiptPrinterListenerInterface implements ReceiptPrintListener {
        boolean noPaper;
        boolean noInk;
        boolean addPaper;
        boolean addInk;

        @Override
        public void noInk(ReceiptPrinter printer) {
            boolean noInk = true;
        }

        @Override
        public void inkAdded(ReceiptPrinter printer) {
            noInk = false;
            addInk = true;
        }

        @Override
        public void noPaper(ReceiptPrinter printer) {
            noPaper = true;
        }

        @Override
        public void paperAdded(ReceiptPrinter printer) {
            noPaper = false;
            addPaper = true;

        }

    }
}
