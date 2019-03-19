import React,{ Component } from 'react';
import {Router, Route, Link, IndexRoute} from 'react-router';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom'

import Login from './component/Login';
import HomePage from './component/HomePage';
import BookSpecificTable from './component/BookSpecificTable';
import StatusPoolTable from './component/StatusPoolTable';
import SeeAllTables from './component/SeeAllTables';
import Result from './component/Result';

class App extends Component {
     render() {
         return (
           <BrowserRouter>
                <div>
                     <Route exact path='/' component={Login} />
                     <Route exact path='/pool-project' component={HomePage} />
                     <Route exact path='/pool-project/book-specific-table' component={BookSpecificTable} />
                </div>
           </BrowserRouter>
         )
     }
}
export default App;



ReactDOM.render(
	<App />,
	document.getElementById('react')
)