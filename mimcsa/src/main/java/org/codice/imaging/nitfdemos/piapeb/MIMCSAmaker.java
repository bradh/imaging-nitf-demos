/*
 * Copyright (c) 2016, Brad Hards
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.codice.imaging.nitfdemos.piapeb;

import org.codice.imaging.nitf.core.common.FileType;
import org.codice.imaging.nitf.core.common.NitfFormatException;
import org.codice.imaging.nitf.core.header.NitfHeader;
import org.codice.imaging.nitf.core.header.NitfHeaderFactory;
import org.codice.imaging.nitf.core.tre.Tre;
import org.codice.imaging.nitf.core.tre.TreEntry;
import org.codice.imaging.nitf.core.tre.TreFactory;
import org.codice.imaging.nitf.core.tre.TreSource;
import org.codice.imaging.nitf.fluent.NitfCreationFlow;

/**
 * Demo application to create NITF files containing Shapefile DES.
 */
public class MIMCSAmaker {

    /**
     * @param args the command line arguments
     * @throws NitfFormatException if there was a construction problem...
     */
    public static void main(String[] args) throws NitfFormatException {
        NitfHeader header = NitfHeaderFactory.getDefault(FileType.NITF_TWO_ONE);
        Tre mimcsa = TreFactory.getDefault("MIMCSA", TreSource.ExtendedHeaderData);
        mimcsa.add(new TreEntry("LAYER_ID", "1ad68fbf-d676-4702-9c40-8c264d13177b", "string"));
        mimcsa.add(new TreEntry("NOMINAL_FRAME_RATE", "2.0000000E-01", "real"));
        mimcsa.add(new TreEntry("MIN_FRAME_RATE", "1.9900000E-01", "real"));
        mimcsa.add(new TreEntry("MAX_FRAME_RATE", "2.0100000E-01", "real"));
        mimcsa.add(new TreEntry("T_RSET", "00", "string"));
        mimcsa.add(new TreEntry("MI_REQ_DECODER", "NC", "string"));
        mimcsa.add(new TreEntry("MI_REQ_PROFILE", "Not applicable", "string"));
        mimcsa.add(new TreEntry("MI_REQ_LEVEL", "N/A", "string"));
        header.getTREsRawStructure().add(mimcsa);
        new NitfCreationFlow()
            .fileHeader(() -> { return header; } )
            .write("mimcsa.ntf");
    }

}
