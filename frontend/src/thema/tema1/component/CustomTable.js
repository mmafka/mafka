import React from 'react';
import ReactPaginate from 'react-paginate';

class CustomTable extends React.Component {
    constructor(props)
    {
        super(props);
        let title =props.state.title.map((item) =>
        <th>{item}</th>);
        this.state = {
            handlePageClick: props.handlePageClick,
            pageCount: props.state.pageCount,
            totalRow:props.state.totalRow,
            title:title,
            content:props.state.content
        };
    }

    render() {

    return (
        <div>
        <table id="table" className="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    {this.state.title}
                                </tr>
                                </thead>
                                <tbody>
                                {this.props.state.content}
                                </tbody>
                                <tfoot>
                                <tr>
                                {this.state.title}
                                </tr>
                                </tfoot>
                            </table>
                            <div class="row">
                                <div class="col-sm-12 col-md-5">
                                    <div class="dataTables_info" id="table_info" role="status" aria-live="polite"> {this.props.state.totalRow} -> {this.props.state.startRow} - {this.props.state.endRow}</div>
                                    </div>
                                    <div class="col-sm-12 col-md-7">
                                        <div class="dataTables_paginate paging_simple_numbers" id="table_paginate">
                                        <ReactPaginate
      previousLabel={'<-'}
      previousClassName={'paginate_button page-item previous'}
      previousLinkClassName={'page-link'}
      nextLabel={'->'}
      nextClassName={'paginate_button page-item next'}
      nextLinkClassName={'page-link'}
      pageLinkClassName={'page-link'}
      pageClassName={'paginate_button page-item'}
      breakLabel={'...'}
      breakClassName={'break-me'}
      pageCount={this.props.state.pageCount}
      marginPagesDisplayed={2}
      pageRangeDisplayed={5}
      onPageChange={this.state.handlePageClick}
      containerClassName={'pagination float-right'}
      subContainerClassName={'pages pagination'}
      activeClassName={'active'}
    />
                                            </div>
                                            </div>
                            </div>
                            </div>
);
    }
}

export default CustomTable;
