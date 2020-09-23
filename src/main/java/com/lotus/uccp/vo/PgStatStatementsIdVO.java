package  com.lotus.uccp.vo;


public class PgStatStatementsIdVO   {

	private Long userid;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getDbid() {
		return dbid;
	}
	public void setDbid(Long dbid) {
		this.dbid = dbid;
	}
	public Long getQueryid() {
		return queryid;
	}
	public void setQueryid(Long queryid) {
		this.queryid = queryid;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Long getCalls() {
		return calls;
	}
	public void setCalls(Long calls) {
		this.calls = calls;
	}
	public Double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}
	public Double getMinTime() {
		return minTime;
	}
	public void setMinTime(Double minTime) {
		this.minTime = minTime;
	}
	public Double getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(Double maxTime) {
		this.maxTime = maxTime;
	}
	public Double getMeanTime() {
		return meanTime;
	}
	public void setMeanTime(Double meanTime) {
		this.meanTime = meanTime;
	}
	public Double getStddevTime() {
		return stddevTime;
	}
	public void setStddevTime(Double stddevTime) {
		this.stddevTime = stddevTime;
	}
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public Long getSharedBlksHit() {
		return sharedBlksHit;
	}
	public void setSharedBlksHit(Long sharedBlksHit) {
		this.sharedBlksHit = sharedBlksHit;
	}
	public Long getSharedBlksRead() {
		return sharedBlksRead;
	}
	public void setSharedBlksRead(Long sharedBlksRead) {
		this.sharedBlksRead = sharedBlksRead;
	}
	public Long getSharedBlksDirtied() {
		return sharedBlksDirtied;
	}
	public void setSharedBlksDirtied(Long sharedBlksDirtied) {
		this.sharedBlksDirtied = sharedBlksDirtied;
	}
	public Long getSharedBlksWritten() {
		return sharedBlksWritten;
	}
	public void setSharedBlksWritten(Long sharedBlksWritten) {
		this.sharedBlksWritten = sharedBlksWritten;
	}
	public Long getLocalBlksHit() {
		return localBlksHit;
	}
	public void setLocalBlksHit(Long localBlksHit) {
		this.localBlksHit = localBlksHit;
	}
	public Long getLocalBlksRead() {
		return localBlksRead;
	}
	public void setLocalBlksRead(Long localBlksRead) {
		this.localBlksRead = localBlksRead;
	}
	public Long getLocalBlksDirtied() {
		return localBlksDirtied;
	}
	public void setLocalBlksDirtied(Long localBlksDirtied) {
		this.localBlksDirtied = localBlksDirtied;
	}
	public Long getLocalBlksWritten() {
		return localBlksWritten;
	}
	public void setLocalBlksWritten(Long localBlksWritten) {
		this.localBlksWritten = localBlksWritten;
	}
	public Long getTempBlksRead() {
		return tempBlksRead;
	}
	public void setTempBlksRead(Long tempBlksRead) {
		this.tempBlksRead = tempBlksRead;
	}
	public Long getTempBlksWritten() {
		return tempBlksWritten;
	}
	public void setTempBlksWritten(Long tempBlksWritten) {
		this.tempBlksWritten = tempBlksWritten;
	}
	public Double getBlkReadTime() {
		return blkReadTime;
	}
	public void setBlkReadTime(Double blkReadTime) {
		this.blkReadTime = blkReadTime;
	}
	public Double getBlkWriteTime() {
		return blkWriteTime;
	}
	public void setBlkWriteTime(Double blkWriteTime) {
		this.blkWriteTime = blkWriteTime;
	}
	private Long dbid;
	private Long queryid;
	private String query;
	private Long calls;
	private Double totalTime;
	private Double minTime;
	private Double maxTime;
	private Double meanTime;
	private Double stddevTime;
	private Long rows;
	private Long sharedBlksHit;
	private Long sharedBlksRead;
	private Long sharedBlksDirtied;
	private Long sharedBlksWritten;
	private Long localBlksHit;
	private Long localBlksRead;
	private Long localBlksDirtied;
	private Long tempBlksRead;
	private Long tempBlksWritten;
	private Double blkReadTime;
	private Double blkWriteTime;
    private long localBlksWritten ;
	

	

}
