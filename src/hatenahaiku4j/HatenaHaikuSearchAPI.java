package hatenahaiku4j;

import hatenahaiku4j.util.DateUtil;
import hatenahaiku4j.util.HatenaUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Hatena Haiku Search (http://hhs.trashsuite.org/) by id:trashsuite氏 
 * @author xxxx
 */
public class HatenaHaikuSearchAPI {
	public static enum Sort { ASC, DESC };
	private static final DateFormat START_FMT = new SimpleDateFormat("'start[year]='yyyy'&start[month]='M'&start[day]='d");
	private static final DateFormat END_FMT = new SimpleDateFormat("'end[year]='yyyy'&end[month]='M'&end[day]='d");	

	private String keyword;
	private String query;
	private Date start;
	private Date end;
	private Sort sort;
	private int page;

	public HatenaHaikuSearchAPI() {
		this.reset();
	}
	
	public void reset() {
		this.keyword = "";
		this.query = "";
		this.start = HatenaUtil.parseDate("2007-01-01 00:00:00");
		this.end = DateUtil.getNow();
		this.sort = Sort.DESC;
		this.page = 1;
	}
	
	public HatenaHaikuSearchAPI setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}
	public HatenaHaikuSearchAPI setQuery(String query) {
		this.query = query;
		return this;
	}
	public HatenaHaikuSearchAPI setStart(Date start) {
		this.start = start;
		return this;
	}
	public HatenaHaikuSearchAPI setEnd(Date end) {
		this.end = end;
		return this;
	}
	public HatenaHaikuSearchAPI setSort(Sort sort) {
		this.sort = sort;
		return this;
	}
	
	public List<Status> search() {
		// TODO 実装
		//System.out.println(START_FMT.format(this.start));
		//System.out.println(END_FMT.format(this.end));
		return null;
	}
}
