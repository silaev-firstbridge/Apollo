/*
 * Copyright © 2018-2019 Apollo Foundation
 */
package com.apollocurrency.aplwallet.apl.core.peer;

import com.apollocurrency.aplwallet.api.p2p.FileChunk;
import com.apollocurrency.aplwallet.api.p2p.FileChunkInfo;
import com.apollocurrency.aplwallet.api.p2p.FileDownloadInfo;
import com.apollocurrency.aplwallet.apl.core.peer.statcheck.HasHashSum;
import com.apollocurrency.aplwallet.apl.core.peer.statcheck.PeerFileInfo;
import com.apollocurrency.aplwallet.apl.core.peer.statcheck.PeerValidityDecisionMaker;
import com.apollocurrency.aplwallet.apl.core.peer.statcheck.PeersList;
import com.apollocurrency.aplwallet.apl.util.ChunkedFileOps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * This class performs complete file downloading from peers
 * @author alukin@gmail.com
 */
public class FileDownloader {
    public class Status{
        double completed;
        int chunksTotal;
        int chunksReady;
        List<String> peers; 
    }
    
    private final String fileID;
    private FileDownloadInfo downloadInfo;    
    private List<HasHashSum> goodPeers;
    private List<HasHashSum> badPeers;
    private Status status;
    
    @Inject
    DownloadableFilesManager manager;
    
    public FileDownloader(String fileID) {
        this.fileID = fileID;
    }
    

    public Status getDownloadStatus(){
        status.completed=1.0D*status.chunksTotal/status.chunksReady;
        return status;
    }

    
    public PeerValidityDecisionMaker.Decision prepareForDownloading(){
        PeerValidityDecisionMaker.Decision res;
        Set<PeerFileInfo> allPeers = getAllAvailablePeers();
        PeersList pl = new PeersList();
        allPeers.forEach((pi) -> {
            pl.add(pi);
        });
        PeerValidityDecisionMaker pvdm = new PeerValidityDecisionMaker(pl);
        res=pvdm.calcualteNetworkState();
        goodPeers = pvdm.getValidPeers();
        badPeers = pvdm.getInvalidPeers();
        return res;
    }
    
    private synchronized FileChunkInfo getNextEmptyChunk(){
       FileChunkInfo res = null;
       for(FileChunkInfo fci: downloadInfo.chunks){
           if(fci.present<1){
               res=fci;
               break;
           }
       }
       return res;
    }
    
    private FileChunk downloadChunk(FileChunkInfo fci, Peer peer){
        fci.present=1;
        //download
        fci.present=2;
        return  null;
    }
    
    private void doPeerDownload(Peer p) throws IOException{
        FileChunkInfo fci = getNextEmptyChunk();        
        ChunkedFileOps fops = new ChunkedFileOps(manager.mapFileIdToLocalPath(fileID));
        while(fci!=null){
            FileChunk fc = downloadChunk(fci, p);
            byte[] data = new byte[fc.info.size];
            fops.writeChunk(fc.info.offset, data, fc.info.crc);
            status.chunksReady++;
            fci.present=3;
            fci = getNextEmptyChunk();
        }
    }
    
    public Status download(){
       for(HasHashSum p:goodPeers){
           
       }
           
       return status;
    }
    
    public Set<PeerFileInfo> getAllAvailablePeers(){
        Set<PeerFileInfo> res = new HashSet<>();
        Collection<? extends Peer> knownPeers = Peers.getAllPeers();
        for(Peer p: knownPeers){
            PeerFileInfo pi=new PeerFileInfo(p, fileID);
            res.add(pi);
        }
        //TODO: should we connect to more peers and get more peers here?
        return res;
    }
}
