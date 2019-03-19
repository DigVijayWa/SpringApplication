import React,{ Component } from 'react';
import ButtonCustom from './ButtonCustom.js';
import TextCustom from './TextCustom.js';

class LoginCustom extends Component {
	constructor(props) {
		super(props);
		this.state = {formLegend : props.formLegend ? props.formLegend : "Default"};
	}
	render() {
		return(
			<div class="form-login">
			<form>
				<h1>{this.state.formLegend}</h1>
  				<hr/>
				<div>
					<div class="field-item">
					<TextCustom text="Enter Employee ID" id="input-1" class="input-1"/>
					</div>
					<div class="field-item">
					<TextCustom text="Password" id="input-2" type="password" class="input-1"/>
					</div>
					<div class="field-item">
					<ButtonCustom class="normalButton" text="Submit" />
					</div>
				</div>
			</form>
			</div>
		);
	}
}
export default LoginCustom;