/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.OHLCStudy;

public class StudyOHLC extends OHLCStudy
{
    @Override
    public void init()
    {
        open = open();
        high = high();
        low = low();
        close = close();
    }
}
