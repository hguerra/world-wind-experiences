package br.com.inpe.worldwind.database;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DataLoaderScience {
	private List<GeometryRecord> geometryRecords;
	private ObjectContainer dataBase;
	// Singleton
	private static DataLoaderScience uniqueInstance;

	private DataLoaderScience() {
		geometryRecords = new LinkedList<GeometryRecord>();
		dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"../WorldWind/database/data.db4o");
	}

	// Singleton
	public static DataLoaderScience getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DataLoaderScience();
		}
		return uniqueInstance;
	}

	public void addData(GeometryRecord geometryRecord) {
		dataBase.store(geometryRecord);
	}

	public List<GeometryRecord> SearchDataMunicipalityName(String parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesMunicipalityName(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}
	
	public List<GeometryRecord> SearchDataBiggestMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesBiggestMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}
	
	public List<GeometryRecord> SearchDataSmallestMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesSmallestMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataGeometry(String parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesGeometry(parameter))
				result.add(geo);
		}
		return result;
	}

	public void remevoDataMunicipalityName(String parameter) {
		List<GeometryRecord> result = SearchDataMunicipalityName(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}

	public void removeDataMunicipalityArea(long parameter) {
		List<GeometryRecord> result = SearchDataMunicipalityArea(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}

	public void removeSearchDataGeometry(String parameter) {
		List<GeometryRecord> result = SearchDataGeometry(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}
}
