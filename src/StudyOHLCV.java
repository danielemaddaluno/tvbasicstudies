/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.OHLCVStudy;
import studylib.sdk.StudyDecl;

@StudyDecl(studyId = "OHLCV", isHiddenStudy = true, isRebuildFromBasicResolution = true)
public class StudyOHLCV extends OHLCVStudy
{
    @Override
    public void init()
    {
        open = open();
        high = high();
        low = low();
        close = close();
        volume = volume();
    }
}
