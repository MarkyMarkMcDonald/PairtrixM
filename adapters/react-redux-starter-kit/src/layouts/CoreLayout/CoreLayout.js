import React, { PropTypes } from 'react';
import '../../styles/core.scss';

export class CoreLayout extends React.Component {
  static propTypes = {
    children: PropTypes.element
  }

  render () {
    return (
      <div>
        {this.props.children}
      </div>
    );
  }
}

export default CoreLayout;
