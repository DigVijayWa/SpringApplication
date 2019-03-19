import React,{ Component } from 'react';

class TextCustom extends React.Component{
	constructor(props) {
		super(props);
		this.state = {type : props.type ?  props.type : 'text', text : props.text ?  props.text : 'default', id : props.id, class : props.class ?  props.class : 'def-class', divClass : props.divClass ? props.divClass : 'col-3'};
	}
	render() {
		return(
				<div class={this.state.divClass}>
      				<input type={this.state.type} placeholder={this.state.text} id={this.state.id} class={this.state.class}/>
      			 	<span class="focus-border"></span>
      			</div>
		);
	}
}
export default TextCustom;