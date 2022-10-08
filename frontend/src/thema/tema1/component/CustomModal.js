import React from 'react';

class CustomModal extends React.Component {
    constructor(props)
    {
        super(props);
        this.state = {
            funcModal: props.funcModal
        };
    }

    render() {

    return (
        <div>
               <div class="modal fade" id="modal-default">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">{this.props.title}</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            {this.props.content}
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Hayır </button>
              <button type="button" class="btn btn-primary" onClick={this.state.funcModal}>Evet</button>
            </div>
          </div>
        </div>
      </div>
                            </div>
);
    }
}
const button = {
    marginTop: "0px",
    marginLeft: "10px",
    float: "left",
    width: "100px"
};
export default CustomModal;
