/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rub.nds.tlsscanner.report.result;

import de.rub.nds.tlsscanner.constants.ProbeType;
import de.rub.nds.tlsscanner.probe.certificate.CertificateJudger;
import de.rub.nds.tlsscanner.probe.certificate.CertificateReport;
import de.rub.nds.tlsscanner.report.SiteReport;
import java.util.List;

/**
 *
 * @author Robert Merget <robert.merget@rub.de>
 */
public class CertificateResult extends ProbeResult {
    
    private List<CertificateReport> reportList;
    
    private boolean expiredCertificates = false;
    private boolean notYetValidCertificates = false;
    private boolean weakHashAlgorithms = false;
    private boolean weakSignatureAlgorithms = false;
    private boolean matchesDomain = false;
    private boolean isTrusted = true;
    private boolean containsBlacklisted = false;
    
    public CertificateResult(ProbeType type, List<CertificateReport> reportList) {
        super(type);
        this.reportList = reportList;
    }
    
    @Override
    public void merge(SiteReport report) {
        report.setCertificateReports(reportList);
        for (CertificateReport certReport : reportList) {
            CertificateJudger judger = new CertificateJudger(certReport.getCertificate(), certReport, report.getHost());
            expiredCertificates |= judger.checkExpired();
            notYetValidCertificates |= judger.checkNotYetValid();
            weakHashAlgorithms |= judger.isWeakHashAlgo(certReport);
            weakSignatureAlgorithms |= judger.isWeakSigAlgo(certReport);
            
            
        }
        report.setCertificateExpired(expiredCertificates);
        report.setCertificateNotYetValid(notYetValidCertificates);
        report.setCertificateHasWeakHashAlgorithm(weakHashAlgorithms);
        report.setCertificateHasWeakSignAlgorithm(weakSignatureAlgorithms);
        
    }
    
}
