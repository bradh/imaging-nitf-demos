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
package org.codice.imaging.nitfdemos.csshpa;

import java.io.File;
import org.codice.imaging.nitf.core.common.FileType;
import org.codice.imaging.nitf.core.common.NitfFormatException;
import org.codice.imaging.nitf.core.dataextension.DataExtensionSegment;
import org.codice.imaging.nitf.core.dataextension.DataExtensionSegmentFactory;
import org.codice.imaging.nitf.core.header.NitfHeaderFactory;
import org.codice.imaging.nitf.fluent.NitfCreationFlow;

/**
 * Demo application to create NITF files containing Shapefile DES.
 */
public class NitfShapeMaker {

    /**
     * @param args the command line arguments
     * @throws NitfFormatException if there was a problem building the shapefile DES.
     */
    public static void main(String[] args) throws NitfFormatException {
        DataExtensionSegment csshpa = DataExtensionSegmentFactory.getCSSHPA(FileType.NITF_TWO_ONE, new File("simplepoint.shp"), new File("simplepoint.shx"), new File("simplepoint.dbf"));
        new NitfCreationFlow()
                .fileHeader(() -> NitfHeaderFactory.getDefault(FileType.NITF_TWO_ONE))
                .dataExtensionSegment(() -> { return csshpa; })
                .write("csshpa_simplepoint.ntf");

        DataExtensionSegment utf8 = DataExtensionSegmentFactory.getCSSHPA(FileType.NITF_TWO_ONE, new File("encoding2.shp"), new File("encoding2.shx"), new File("encoding2.dbf"));
        new NitfCreationFlow()
                .fileHeader(() -> NitfHeaderFactory.getDefault(FileType.NITF_TWO_ONE))
                .dataExtensionSegment(() -> { return utf8; })
                .write("csshpa_toyko.ntf");
    }

}
