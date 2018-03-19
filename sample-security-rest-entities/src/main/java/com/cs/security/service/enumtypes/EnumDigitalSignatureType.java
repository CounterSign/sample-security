/*
Copyright (c) 2018 CounterSign, www.countersign.com.br

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 
 NOTICE: this code was extracted from the CSPKI Library to simplify the 
 		 Digital Signature Sample using the CounterSign Security Rest API.
*/

package com.cs.security.service.enumtypes;

public enum EnumDigitalSignatureType {
	DS_CMS(1),
	DS_CMS_COSIGN(2),
	DS_CMS_COUNTERSIGN(3),
	DS_CMS_DETACHED(4),
	DS_CMS_COSIGN_DETACHED(5),
	DS_CMS_COUNTERSIGN_DETACHED(6),
	DS_CMS_TS(11),
	DS_CMS_TS_COSIGN(12),
	DS_CMS_TS_COUNTERSIGN(13),
	DS_CMS_TS_DETACHED(14),
	DS_CMS_TS_COSIGN_DETACHED(15),
	DS_CMS_TS_COUNTERSIGN_DETACHED(16),
	
	DS_ICP_AD_RB(101),
	DS_ICP_AD_RB_COSIGN(102),
	DS_ICP_AD_RB_COUNTERSIGN(103),
	
	DS_ICP_AD_RT(201),
	DS_ICP_AD_RT_COSIGN(202),
	DS_ICP_AD_RT_COUNTERSIGN(202),
	
	DS_ICP_AD_RV(301),
	DS_ICP_AD_RV_COSIGN(302),
	DS_ICP_AD_RV_COUNTERSIGN(302),
	
	DS_ICP_AD_RC(401),
	DS_ICP_AD_RC_COSIGN(402),
	DS_ICP_AD_RC_COUNTERSIGN(402),
	
	DS_ICP_AD_RA(501),
	DS_ICP_AD_RA_COSIGN(502),
	DS_ICP_AD_RA_COUNTERSIGN(502),
	
	DS_AUTH(1001),
	
	VS_CMS(2001),
	VS_ICP(2002);
    
    protected int id = 1;

    EnumDigitalSignatureType() {
    }

    EnumDigitalSignatureType(int id) {
        this.id = id;
    }

    EnumDigitalSignatureType(EnumDigitalSignatureType instance) {
        id = instance.value();
    }

    public final int value() { return id; }

    public String getDescription() {
        return getDescription(this.value());
    }

    public static String getDescription(int i) {
    	if(i == EnumDigitalSignatureType.DS_CMS.value()) {
            return "Assinatura Digital CMS";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COSIGN.value()) {
            return "Co-Assinatura Digital CMS";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital CMS";
        } else if(i == EnumDigitalSignatureType.DS_CMS_DETACHED.value()) {
            return "Assinatura Digital CMS Detached";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COSIGN_DETACHED.value()) {
            return "Co-Assinatura Digital CMS Detached";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COUNTERSIGN_DETACHED.value()) {
            return "Contra-Assinatura Digital CMS Detached";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS.value()) {
            return "Assinatura Digital CMS com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COSIGN.value()) {
            return "Co-Assinatura Digital CMS com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital CMS com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_DETACHED.value()) {
            return "Assinatura Digital CMS Detached com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COSIGN_DETACHED.value()) {
            return "Co-Assinatura Digital CMS Detached com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COUNTERSIGN_DETACHED.value()) {
            return "Contra-Assinatura Digital CMS Detached com Carimbo do Tempo";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB.value()) {
            return "Assinatura Digital ICP-Brasil AD-RB";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB_COSIGN.value()) {
            return "Co-Assinatura Digital ICP-Brasil AD-RB";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital ICP-Brasil AD-RB";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT.value()) {
            return "Assinatura Digital ICP-Brasil AD-RT";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT_COSIGN.value()) {
            return "Co-Assinatura Digital ICP-Brasil AD-RT";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital ICP-Brasil AD-RT";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV.value()) {
            return "Assinatura Digital ICP-Brasil AD-RV";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV_COSIGN.value()) {
            return "Co-Assinatura Digital ICP-Brasil AD-RV";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital ICP-Brasil AD-RV";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC.value()) {
            return "Assinatura Digital ICP-Brasil AD-RC";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC_COSIGN.value()) {
            return "Co-Assinatura Digital ICP-Brasil AD-RC";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital ICP-Brasil AD-RC";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA.value()) {
            return "Assinatura Digital ICP-Brasil AD-RA";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA_COSIGN.value()) {
            return "Co-Assinatura Digital ICP-Brasil AD-RA";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA_COUNTERSIGN.value()) {
            return "Contra-Assinatura Digital ICP-Brasil AD-RA";
        } else if(i == EnumDigitalSignatureType.DS_AUTH.value()) {
            return "Autenticação com Assinatura Digital";
		}        
        return null;
    }

	public static EnumDigitalSignatureType getPolicyFromOid(String policyOid) {
		if(policyOid.contains("2 16 76 1 7 1 1")) {
			return EnumDigitalSignatureType.DS_ICP_AD_RB;
		} else if(policyOid.contains("2 16 76 1 7 1 2")) {
			return EnumDigitalSignatureType.DS_ICP_AD_RT;
		} else if(policyOid.contains("2 16 76 1 7 1 3")) {
			return EnumDigitalSignatureType.DS_ICP_AD_RV;
		} else if(policyOid.contains("2 16 76 1 7 1 4")) {
			return EnumDigitalSignatureType.DS_ICP_AD_RC;
		} else if(policyOid.contains("2 16 76 1 7 1 5")) {
			return EnumDigitalSignatureType.DS_ICP_AD_RA;
		}
        return EnumDigitalSignatureType.DS_CMS;
	}
    
    public String getPolicyOid() {
        return getPolicyOid(this.value());
    }

	public static String getPolicyOid(int i) {
    	if(i == EnumDigitalSignatureType.DS_CMS.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COSIGN.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COUNTERSIGN.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COSIGN_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_COUNTERSIGN_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COSIGN.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COUNTERSIGN.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COSIGN_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_CMS_TS_COUNTERSIGN_DETACHED.value()) {
            return "";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB.value()) {
            return "2 16 76 1 7 1 1 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB_COSIGN.value()) {
            return "2 16 76 1 7 1 1 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RB_COUNTERSIGN.value()) {
            return "2 16 76 1 7 1 1 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT.value()) {
            return "2 16 76 1 7 1 2 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT_COSIGN.value()) {
            return "2 16 76 1 7 1 2 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RT_COUNTERSIGN.value()) {
            return "2 16 76 1 7 1 2 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV.value()) {
            return "2 16 76 1 7 1 3 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV_COSIGN.value()) {
            return "2 16 76 1 7 1 3 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RV_COUNTERSIGN.value()) {
            return "2 16 76 1 7 1 3 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC.value()) {
            return "2 16 76 1 7 1 4 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC_COSIGN.value()) {
            return "2 16 76 1 7 1 4 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RC_COUNTERSIGN.value()) {
            return "2 16 76 1 7 1 4 2 2";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA.value()) {
            return "2 16 76 1 7 1 5 2 3";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA_COSIGN.value()) {
            return "2 16 76 1 7 1 5 2 3";
        } else if(i == EnumDigitalSignatureType.DS_ICP_AD_RA_COUNTERSIGN.value()) {
            return "2 16 76 1 7 1 5 2 4";
        } else if(i == EnumDigitalSignatureType.DS_AUTH.value()) {
            return "";
		}        
        return null;
	}
    
	
}
