package com.eaphonetech.common.datatables.model.mapping;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class QueryOutput<T> {

  /**
   * Total records, before filtering (i.e. the total number of records in the database)
   */
  @JsonView(View.class)
  private long total = 0L;

  /**
   * Total records, after filtering (i.e. the total number of records after filtering has been
   * applied - not just the number of records being returned for this page of data).
   */
  @JsonView(View.class)
  private long filtered = 0L;

  /**
   * The data to be displayed in the table. This is an array of data source objects, one for each
   * row, which will be used by DataTables. Note that this parameter's name can be changed using the
   * ajaxDT option's dataSrc property.
   */
  @JsonView(View.class)
  private List<T> data = Collections.emptyList();

  /**
   * Optional: If an error occurs during the running of the server-side processing script, you can
   * inform the user of this error by passing back the error message to be displayed using this
   * parameter. Do not include if there is no error.
   */
  @JsonView(View.class)
  private String error;

  public interface View {
  }

}
