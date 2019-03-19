import React,{ Component } from 'react';
import Pool from './Pool.js';

class PoolList extends React.Component{
	render() {
		const pool = this.props.pool.map(poolTable =>
			<Pool key={poolTable._links.self.href} pool={poolTable}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<th>Pool Name</th>
						<th>Condition</th>
						<th>Building Name</th>
						<th>Floor No</th>
					</tr>
					{pool}
				</tbody>
			</table>
		)
	}
}
export default PoolList;