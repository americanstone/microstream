package one.microstream.integrations.spring.boot.types.sql;

/*-
 * #%L
 * microstream-integrations-spring-boot
 * %%
 * Copyright (C) 2019 - 2023 MicroStream Software
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

public class AbstractSqlConfiguration
{

    private String dataSourceProvider;
    private String catalog;
    private String schema;
    private String url;
    private String user;
    private String password;

    public String getDataSourceProvider()
    {
        return this.dataSourceProvider;
    }

    public void setDataSourceProvider(final String dataSourceProvider)
    {
        this.dataSourceProvider = dataSourceProvider;
    }

    public String getCatalog()
    {
        return this.catalog;
    }

    public void setCatalog(final String catalog)
    {
        this.catalog = catalog;
    }

    public String getSchema()
    {
        return this.schema;
    }

    public void setSchema(final String schema)
    {
        this.schema = schema;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    public String getUser()
    {
        return this.user;
    }

    public void setUser(final String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }
}
