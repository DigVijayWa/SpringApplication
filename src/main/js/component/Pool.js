import React,{ Component } from 'react';

class Pool extends React.Component{
    componentDidMount() {
        client({method: 'GET', path: this.props.pool._links.building}).done(response => {
            			this.props.pool.buildingName = response.entity.buildingName;
            		});
    }

	render() {
		return (
			<tr>
				<td>{this.props.pool.id}</td>
				<td>{this.props.pool.poolName}</td>
				<td>{this.props.pool.poolCondition}</td>
				<td>{this.props.pool.buildingName}</td>
				<td>{this.props.pool.floorNo}</td>
			</tr>
		)
	}
}
export default Pool;