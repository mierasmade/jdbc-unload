/*******************************************************************************
 * Copyright 2017 Mieras Made
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package nl.mierasmade.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.mierasmade.configuration.Configuration;
import nl.mierasmade.mapper.RecordMapper;
import nl.mierasmade.record.Record;

@Component
public class JdbcItemReader {
	
	@Autowired
	private Configuration configuration;	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private RecordMapper recordMapper;
	
	public JdbcPagingItemReader<Record> constructJdbcPagingItemReader(PagingQueryProvider pagingQueryProvider) {
		JdbcPagingItemReader<Record> reader = new JdbcPagingItemReader<>();		
		reader.setDataSource(dataSource);
		reader.setRowMapper(recordMapper);
		reader.setQueryProvider(pagingQueryProvider);
		reader.setPageSize(configuration.getPageSize());
		try {
			reader.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}
}
