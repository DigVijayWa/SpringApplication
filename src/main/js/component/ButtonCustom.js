import React,{ Component } from 'react';

class ButtonCustom extends Component {
	constructor(props) {
		super(props);
		this.state = {text : props.text , id : props.id , class : props.class, onPress : props.onPress ? () => props.navigation.navigate(props.onPress) : () => props.navigation.navigate('MainApp')  };
	}
	render() {
		return(
			<div id="button-class">
        		<button id={this.state.id} class={this.state.class} onClick={this.state.onPress}> {this.state.text}  </button>
        	</div>
		);
	}
}
export default ButtonCustom;