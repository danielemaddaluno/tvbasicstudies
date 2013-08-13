/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;


//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "Overlay", title = "Overlay", shortTitle = "Ovl")
public class StudyOverlay extends OHLCStudy
{
    @StudyArgSymbol(name = "symbol", defval = "GOOG")
    String sym;

    @Override
    public void init()
    {
        final StdLib overlay = overlay(sym, resolution());
        open = adoptPrecise(overlay.open());
        high = adoptPrecise(overlay.high());
        low = adoptPrecise(overlay.low());
        close = adoptPrecise(overlay.close());
    }
}
