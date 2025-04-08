import type {
  OpenAPIClient,
  Parameters,
  UnknownParamsObject,
  OperationResponse,
  AxiosRequestConfig,
} from 'openapi-client-axios';

declare namespace Paths {
    namespace AprovarCoordenador {
        namespace Parameters {
            export type IdDisciplina = string;
            export type Rm = string;
        }
        export interface PathParameters {
            rm: Parameters.Rm;
            idDisciplina: Parameters.IdDisciplina;
        }
    }
    namespace AprovarSecretaria {
        namespace Parameters {
            export type IdDisciplina = string;
            export type Rm = string;
        }
        export interface PathParameters {
            rm: Parameters.Rm;
            idDisciplina: Parameters.IdDisciplina;
        }
    }
    namespace Matricular {
        namespace Parameters {
            export type IdDisciplina = string;
            export type Rm = string;
        }
        export interface PathParameters {
            rm: Parameters.Rm;
            idDisciplina: Parameters.IdDisciplina;
        }
        namespace Responses {
            export type $200 = string;
            export interface $404 {
            }
        }
    }
    namespace SolicitarCancelamento {
        namespace Parameters {
            export type IdDisciplina = string;
            export type Rm = string;
        }
        export interface PathParameters {
            rm: Parameters.Rm;
            idDisciplina: Parameters.IdDisciplina;
        }
    }
}

export interface OperationMethods {
  /**
   * matricular - Matricular aluno
   * 
   * Efetua a matrícula de um aluno em uma disciplina
   */
  'matricular'(
    parameters?: Parameters<Paths.Matricular.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Matricular.Responses.$200>
  /**
   * solicitarCancelamento
   */
  'solicitarCancelamento'(
    parameters?: Parameters<Paths.SolicitarCancelamento.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<any>
  /**
   * aprovarSecretaria
   */
  'aprovarSecretaria'(
    parameters?: Parameters<Paths.AprovarSecretaria.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<any>
  /**
   * aprovarCoordenador
   */
  'aprovarCoordenador'(
    parameters?: Parameters<Paths.AprovarCoordenador.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<any>
}

export interface PathsDictionary {
  ['/faculdade/matricula/{rm}/{idDisciplina}']: {
    /**
     * matricular - Matricular aluno
     * 
     * Efetua a matrícula de um aluno em uma disciplina
     */
    'put'(
      parameters?: Parameters<Paths.Matricular.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Matricular.Responses.$200>
  }
  ['/faculdade/matricula/cancelar/solicitar/{rm}/{idDisciplina}']: {
    /**
     * solicitarCancelamento
     */
    'put'(
      parameters?: Parameters<Paths.SolicitarCancelamento.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<any>
  }
  ['/faculdade/matricula/cancelar/aprovar/secretaria/{rm}/{idDisciplina}']: {
    /**
     * aprovarSecretaria
     */
    'put'(
      parameters?: Parameters<Paths.AprovarSecretaria.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<any>
  }
  ['/faculdade/matricula/cancelar/aprovar/coordenador/{rm}/{idDisciplina}']: {
    /**
     * aprovarCoordenador
     */
    'put'(
      parameters?: Parameters<Paths.AprovarCoordenador.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<any>
  }
}

export type Client = OpenAPIClient<OperationMethods, PathsDictionary>
