package services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import models.Bar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryDefinition;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;

@Service
public class BarServiceMarkLogic implements BarService {
	@Autowired
	private DatabaseClient db;

	@Override
	public void addBar(final Bar bar) {
		try {
			JAXBContext context = JAXBContext.newInstance(Bar.class);
			JAXBHandle writer = new JAXBHandle(context);
			XMLDocumentManager docMgr = db.newXMLDocumentManager();
			writer.set(bar);
			docMgr.write(bar.getName(), writer);
			db.release();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Bar> getAllBars() {
		List<Bar> bars = new ArrayList<Bar>();

		QueryManager qm = db.newQueryManager();
		StructuredQueryBuilder sqb = new StructuredQueryBuilder();
		QueryDefinition sqd = sqb.collection("asdf");
		SearchHandle handle = qm.search(sqd, new SearchHandle());
		for (MatchDocumentSummary sum : handle.getMatchResults()) {
			StringHandle out = sum.getFirstSnippet(new StringHandle());
			Bar bar = new Bar();
			bar.setName("asdf" + out.get());
			bars.add(bar);
		}
		return bars;
	}
}
