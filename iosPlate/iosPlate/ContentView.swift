import SwiftUI
import shared

@MainActor
class HomeViewModelWrapper: ObservableObject {
    let homeViewModel: HomeViewModel
    @Published var vehicleState: VehicleState? = nil
    @Published var placeInput: String = ""
    
    init() {
        self.homeViewModel = HomeViewModel()
    }
    
    func startObserving() {
        Task {
            for await state in homeViewModel.vehicleState {
                self.vehicleState = state
            }
        }
    }
}

struct ContentView: View {
    
    @StateObject private var viewModel: HomeViewModelWrapper = .init()

	var body: some View {
        VStack {
            if let vehicleState = viewModel.vehicleState {
                if let vehicle = vehicleState.vehicle {
                    Text("Vehicle: \(vehicle.brand) \(vehicle.engine)")
                }
            }
            
            TextField("Plate", text: $viewModel.placeInput)
            
            Button {
                viewModel.homeViewModel.fetchVehicleFromPlate()
            } label: {
                Text("Search for plate")
            }
            .onChange(of: viewModel.placeInput) { newValue in
                viewModel.homeViewModel.updatePlateInput(newInput: newValue)
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
